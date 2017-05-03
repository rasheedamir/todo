package io.as.todo.search.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Value
@Document(indexName = "todo", type = "todo", shards = 1, replicas = 0, refreshInterval = "-1")
@Setting(settingPath = "/es/settings.json")
@JsonDeserialize(builder = ToDoEntity4Search.Builder.class)
public class ToDoEntity4Search
{
    @Id
    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.not_analyzed)
    private final String id;

    @NotNull
    @NotEmpty
    @MultiField(mainField = @Field(type = FieldType.String, store = true, index = FieldIndex.analyzed, searchAnalyzer = "whitespace_analyzer", analyzer = "edge_nGram_analyzer"),
            otherFields = {
                    @InnerField(suffix = "exact", type = FieldType.String, store = true, index = FieldIndex.not_analyzed)
            })
    private final String title;

    @Field(type = FieldType.Boolean,
            store = true,
            index = FieldIndex.not_analyzed)
    private final boolean finished;

    @Field(type = FieldType.Date,
            store = true,
            index = FieldIndex.not_analyzed)
    private final Date createdAt;

    @lombok.Builder(builderClassName = "Builder", builderMethodName = "newBuilder", toBuilder = true)
    private ToDoEntity4Search(String id, @NonNull String title, boolean finished, Date createdAt)
    {
        this.id = id;
        this.title = title;
        this.finished = finished;
        this.createdAt = createdAt;
    }

    /*
    breaks mapper! & I don't understand yet why it breaks it
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder
    {
    }
    */
}
