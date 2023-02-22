package com.company.repositories.interfaces;

import com.company.entities.Client;

import java.util.List;

public interface IClientRepository {

    Client Registration(Client client);

    Client Sign_in(String username, String password);

    List<Client> getAllClients();




}
