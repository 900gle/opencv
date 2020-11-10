package com.etoos.opencv.apis;

import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

public class ImageIndexApi {

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
                builder.startObject("feature");
                {
                    builder.field("type", "dense_vector")
                            .field("dims", "128")
                    ;
                }
                builder.endObject();

                builder.startObject("image_id");
                {
                    builder.field("type", "text");
                }
                builder.endObject();

                builder.startObject("image_name");
                {
                    builder.field("type", "text");
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
