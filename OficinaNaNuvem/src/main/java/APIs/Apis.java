package APIs;

import com.thinkcode.models.Criptografia;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Apis extends Criptografia {

    //gera o token para consumo da api
    public static String Token() {

        String token = "";

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "password=3lQRe1t2AgSmlQBw&grant_type=password&Username=o9b4APaaYTlR");
            Request request = new Request.Builder()
                    .url("https://dev.pinbank.com.br/services/api/token")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            okhttp3.Response response = client.newCall(request).execute();

            String rbody = response.body().string();

            JSONObject obj = new JSONObject(rbody);
            if (obj.has("access_token")) {
                token = obj.getString("access_token");
            } else {
                token = "";
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(Apis.class.getName()).log(Level.SEVERE, null, ex);
            token = "";
        }
        return token;
    }

    //monta a request de consumo da api
    public static String Request(String url, String json) {

        String response = "";

        try {
            byte[] formatado = json.getBytes("windows-1252");
            json = new String(formatado, "UTF-8");
            String requestEncrypted = encrypt(json);
            String token = "Bearer " + Token();
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{'Data':{'Json':'" + requestEncrypted + "'}}");
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Authorization", token)
                    .addHeader("Username", "o9b4APaaYTlR")
                    .addHeader("RequestOrigin", "5")
                    .addHeader("Content-Type", "application/json")
                    .build();
            okhttp3.Response responseEncrypted = client.newCall(request).execute();
            JSONObject obj = new JSONObject(responseEncrypted.body().string());

            if (obj.has("ResultCode")) {
                int result = obj.getInt("ResultCode");

                if (result == 0) {
                    if (obj.has("Data")) {
                        String dadoFechado = obj.getString("Data");
                        JSONObject obj2 = new JSONObject(dadoFechado);
                        if (obj2.has("Json")) {
                            String jsonResponse = obj2.getString("Json");
                            response = decrypt(jsonResponse);
                        }
                    }
                } else {
                    if (obj.has("Message")) {
                        String message = obj.getString("Message");
                        response = message;
                    }
                }

            }

        } catch (IOException | JSONException ex) {
            Logger.getLogger(Apis.class.getName()).log(Level.SEVERE, null, ex);
            response = ex.toString();
        }
        return response;
    }

    //consulta cep
    public static String ConsultaCep(String cep) {
        if ((cep == "")) {
            cep = "05750320";
        }
        String dados = "";
        String jsonAberto = "{'Data':{'Cep':'" + cep + "'}}";
        String url = "https://dev.pinbank.com.br/services/api/ContaDigital/ConsultarCepEncrypted";

        dados = Request(url, jsonAberto);
        return dados;
    }

    //Gerar Boleto
    public static String GerarBoleto(String cpf, String nome, String email, int valor) {
        String boleto = "";
        String jsonAberto = "";

        // Cria um formatador para a data usando DateFormat:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar gc = Calendar.getInstance();
        // Adiciona 5 dias:
        gc.add((GregorianCalendar.DAY_OF_MONTH), 5);
        String data = sdf.format(gc.getTime()).toString();

        jsonAberto = "{'Data':{'CodigoCanal':47,'CodigoCliente':3510,'DataVencimento':'" + data + "','Valor':" + valor + ",'Email':'" + email + "','DadosSacado':{'CpfCnpj':'" + cpf + "','Nome':'" + nome + "'},'RetornarBase64':true}}";

        String url = " https://dev.pinbank.com.br/services/api/CashIn/GerarBoletoEncrypted";

        boleto = Request(url, jsonAberto);
        return boleto;
    }

    //EfetuarTransacao Cartao
    public static String EfetuarTransacao(String nome, String dataValidade, String numeroCartao, String cvv, int valor, int qtdeParcelas) {
        String dados = "";
        String jsonAberto = "";
        int pagamento;
        if (qtdeParcelas == 1) {
            pagamento = 1;
        } else {
            pagamento = 2;
        }
        if (dataValidade.length() > 6) {
            dados = "Data inválida";
            return dados;
        }

        jsonAberto = "{'Data':{'CodigoCanal':47,'CodigoCliente':3510,'KeyLoja':'11384322623341877660','NomeImpresso':'" + nome + "','DataValidade':'" + dataValidade + "','NumeroCartao':'" + numeroCartao + "','CodigoSeguranca':'" + cvv + "','Valor':" + valor + ",'FormaPagamento':'" + pagamento + "','QuantidadeParcelas':" + qtdeParcelas + ",'DescricaoPedido':'TESTE','TransacaoPreAutorizada':false}}";

        String url = " https://dev.pinbank.com.br/services/api/Transacoes/EfetuarTransacaoEncrypted";
        dados = Request(url, jsonAberto);
        return dados;
    }

}
