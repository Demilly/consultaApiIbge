package br.com.kafkaline.ibgewrapper.service.cidade;

import br.com.kafkaline.ibgewrapper.gateway.feign.CidadeClient;
import br.com.kafkaline.ibgewrapper.gateway.feign.EstadoClient;
import br.com.kafkaline.ibgewrapper.gateway.json.CidadeJson;
import br.com.kafkaline.ibgewrapper.gateway.json.EstadoJson;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarCidadePorCodigoService {

    public List<CidadeJson> execute(String estado) {
        long tempoInicial = System.currentTimeMillis();

        CidadeClient cidadeClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(CidadeClient.class, "https://servicodados.ibge.gov.br");

        List<CidadeJson> cidadeJsons = cidadeClient.get(estado);

        System.out.printf("%.3f ms%n", (System.currentTimeMillis() - tempoInicial) / 1000d);

        return cidadeJsons;
    }
}
