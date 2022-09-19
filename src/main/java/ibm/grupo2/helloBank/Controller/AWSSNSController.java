package ibm.grupo2.helloBank.Controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.AmazonSNSException;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *  <h1>Métodos SNS</h1>
 * <p>Controller com os metodos responsaveis por enviar sms e cadastrar e-mail do cliente</p>
 * @author Eduardo Silva
 * @since 15/09/2022
 */

public class AWSSNSController {

  private final static String TOPIC_ARN = "arn:aws:sns:us-east-1:296961575577:HelloBank";

 

  AmazonSNSClient amazonSNSClient;


  /**
   * Metodo para casdatrar email do cliente na aws
   * @param email recebe o email do cliente
   * @return retorna mensagem confirmando o envio do email
   * 
   */
  
  
  public void addSubscriptionToSNSTopic(String email) {
    
    SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN, "email", email);
    amazonSNSClient.subscribe(subscribeRequest);
    //return "Subscription request is pending. To confirm the subscription please check your email :"
      //  + email;
  }
  
  

  /**
   * Metodo para casdatrar email do cliente na aws
   * @param message recebe a mensagem que deseja enviar ao cliente
   * @param phoneNumber Recebe o numero do cliente
   * @return retorna mensagem confirmando o envio do sms
   * 
   */
  
  public String pubTextSMS(String message, String phoneNumber) {
      
	  
	  try {
          PublishRequest request = new PublishRequest();
          request.setMessage(message);
          request.setPhoneNumber(phoneNumber);
          

          PublishResult result = amazonSNSClient.publish(request);
          return result.getMessageId() + " Message sent. Status was " + result.getSdkResponseMetadata();

      } catch (AmazonSNSException e) {
          System.err.println(e);
          System.exit(1);
          return "erro";
      }
  }
  
  /**
   * Metodo para cadastrar numero do cliente 
   * @param topicArn recebe o Arn da aws para o qual o numero será cadastrado
   * @param phoneNumber Recebe o numero do cliente para ser cadastrado
   * @return retorna mensagem confirmando o cadastro do numero
   * 
   */
  
  public void subTextSNS(String phoneNumber) {
	
	  
      try {
          SubscribeRequest request = new SubscribeRequest();
              request.setProtocol("sms");
              request.setEndpoint(phoneNumber);
              request.setReturnSubscriptionArn(true);
              request.setTopicArn(TOPIC_ARN);
        		  
        		 
          SubscribeResult result = amazonSNSClient.subscribe(request);
         // System.out.println("Subscription ARN: " + result.getSubscriptionArn() + "\n\n Status is " + result.getSdkHttpMetadata());
         //return "Celular cadastrado com sucesso" + result.getSubscriptionArn();

      } catch (AmazonSNSException e) {
          System.err.println(e);
          System.exit(1);
          //return "Erro" + e;
      }
  }


}