package ibm.grupo2.helloBank.Config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSSNSConfig {

  @Value("${cloud.aws.region.static}")
  private static String region;

  @Value("${cloud.aws.credentials.access-key}")
  private static String awsAccessKey;

  @Value("${cloud.aws.credentials.secret-key}")
  private static String awsSecretKey;

    @Primary
    @Bean
    public static AmazonSNSClient getAWSSNSClient() {
        return (AmazonSNSClient) AmazonSNSClientBuilder.standard()
                .withRegion(region)
                .withCredentials(
                        new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
    }
}