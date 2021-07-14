package co.com.bancolombia.kinesis.operations;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.model.DescribeStreamRequest;
import software.amazon.awssdk.services.kinesis.model.DescribeStreamResponse;
import software.amazon.awssdk.services.kinesis.model.KinesisException;

import java.util.concurrent.CompletableFuture;

//@Component
@RequiredArgsConstructor
@Slf4j
public class KinesisOperations {

    private final KinesisAsyncClient client;

    public <T> void setObjectOnDataStream(T data) {
        Gson gson = new Gson();
        String stringObject = gson.toJson(data);
    }

    private boolean validateStream(String kinesisStream) {
        try {
            DescribeStreamRequest streamRequest = DescribeStreamRequest.builder()
                    .streamName(kinesisStream)
                    .build();
            CompletableFuture<DescribeStreamResponse> streamResponse = client.describeStream(streamRequest);
            if (!streamResponse.join().streamDescription().streamStatus().toString().equals("ACTIVE")) {
                log.info("Stream " + kinesisStream + " is not active. Please wait a few moments and try again.");
                return false;
            }
        } catch (KinesisException e) {
            log.info("Error validating provided Stream, with error: " + e.getMessage());
            return false;
        }
        return true;
    }
}
