package services;

import domain.Client;
import repository.ClientiRepository;
import repository.JdbcUtils;

/**
 * Created by Costi on 19.03.2017.
 */
public class ClientiServices {
    private ClientiRepository clientiRepository;

    public ClientiServices(){
        clientiRepository=new ClientiRepository(JdbcUtils.loadProps());
    }

    public Client findOne(int idClient){
        return clientiRepository.findOne(idClient) ;
    }

    public int findByName(String nume){
        return clientiRepository.findIdByName(nume);
    }

    public void addClient(Client client){
        clientiRepository.save(client);
    }
}
