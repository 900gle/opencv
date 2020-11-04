package com.etoos.opencv.apis;

import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

public class MaskingIndexApi {

    public static CreateIndexRequest createIndex(String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);


        XContentBuilder settingBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .field("number_of_shards", 2)
                .field("number_of_replicas", 0)
                .endObject();
        request.settings(settingBuilder);

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("id");
                {
                    builder.field("type", "long");
                }
                builder.endObject();

                builder.startObject("image_name");
                {
                    builder.field("type", "text");
                }
                builder.endObject();

                builder.startObject("image_path");
                {
                    builder.field("type", "text");
                }
                builder.endObject();

                builder.startObject("original_image_name");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();

                builder.startObject("created_date");
                {
                    builder.field("type", "date")
                            .field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis||strict_date_hour_minute_second_millis||strict_date_optional_time")
                            .field("null_value", "1");
                }
                builder.endObject();

                builder.startObject("modified_date");
                {
                    builder.field("type", "date")
                            .field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis||strict_date_hour_minute_second_millis||strict_date_optional_time")
                            .field("null_value", "1");
                }
                builder.endObject();

            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping(builder);

        return request;
    }
}
