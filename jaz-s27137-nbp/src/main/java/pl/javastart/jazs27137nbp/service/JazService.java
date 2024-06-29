package pl.javastart.jazs27137nbp.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.jazs27137nbp.exception.MyBadRequestException;
import pl.javastart.jazs27137nbp.exception.MyGateWayTimeout;
import pl.javastart.jazs27137nbp.exception.MyInternalServerError;
import pl.javastart.jazs27137nbp.exception.MyNotFoundException;
import pl.javastart.jazs27137nbp.model.NbpApiResponse;
import pl.javastart.jazs27137nbp.model.NbpRate;
import pl.javastart.jazs27137nbp.model.Waluta;
import pl.javastart.jazs27137nbp.repository.JazRepository;

@Service
public class JazService {

    private final JazRepository jazRepository;

    private final RestTemplate restTemplate;

    public JazService(JazRepository jazRepository, RestTemplate restTemplate) {
        this.jazRepository = jazRepository;
        this.restTemplate = restTemplate;
    }

    private final String NBP_API_AV1 = "http://api.nbp.pl/api/exchangerates/rates/a/";
    private final String NBP_API_AV2 = "/";
    private final String NBP_API_AV3 = "/";
//http://api.nbp.pl/api/exchangerates/rates/a/gbp/2012-01-01/2012-01-31/
//    "/2012-01-01/2012-01-31/"
    public double getDaysAve(String code, String start, String stop) {
        try {
            NbpApiResponse response = restTemplate.getForObject(NBP_API_AV1 + code + NBP_API_AV2 + start + NBP_API_AV3 + stop, NbpApiResponse.class);
            return response.getRates().stream()
                    .mapToDouble(NbpRate::getMid)
                    .average()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak danych dla podanej waluty"));
        }catch (HttpClientErrorException.NotFound e) {
            throw new MyNotFoundException();
        } catch (HttpClientErrorException.BadRequest b) {
            throw new MyBadRequestException();
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new MyInternalServerError();
        } catch (HttpServerErrorException.GatewayTimeout m) {
            throw new MyGateWayTimeout();
        }

    }

    //    wyliczanie sredniej wybranej dlugosci dni

    public Waluta getDaysNewAverage(String code, String start, String stop){
        try {
            Waluta waluta = new Waluta();
            waluta.setKurs(getDaysAve(code, start, stop));
            waluta.setCode(code);
            waluta.getData();
            waluta.setDataStart(start);
            waluta.setDataStop(stop);
            jazRepository.save(waluta);
            return waluta;
        }catch (HttpClientErrorException.NotFound e) {
            throw new MyNotFoundException();
        } catch (HttpClientErrorException.BadRequest b) {
            throw new MyBadRequestException();
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new MyInternalServerError();
        } catch (HttpServerErrorException.GatewayTimeout m) {
            throw new MyGateWayTimeout();
        }

    }

}
