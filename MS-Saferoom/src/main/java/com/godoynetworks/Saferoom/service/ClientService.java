package com.godoynetworks.Saferoom.service;
import com.godoynetworks.Saferoom.dto.ClientDTO;
import com.godoynetworks.Saferoom.model.Client;
import com.godoynetworks.Saferoom.model.Role;
import com.godoynetworks.Saferoom.model.exception.ResourceNotFoundException;
import com.godoynetworks.Saferoom.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Method to retrieve all clients.
     * @return every client from the database.
     */
    @Transactional(readOnly = true)
    public List<ClientDTO> findAllClients() {
        List<Client> list = repository.findAll();
        return list.stream().map(client -> new ModelMapper().map(client, ClientDTO.class)).collect(Collectors.toList());
    }

    /**
     * Method that return a client by its ID.
     * @param id of the searched client.
     * @return a client.
     */
    @Transactional(readOnly = true)
    public Optional<ClientDTO> findClientById(Long id) {
        // Obtaining Client Optional by its ID.
        Optional<Client> client = repository.findById(id);
        // If it couldn't find, throw an Exception
        if (client.isEmpty()) {
            throw new ResourceNotFoundException("Client ID " + id + " could not be found.");
        }
        // Convert the found Optional into a DTO.
        ClientDTO dto = new ModelMapper().map(client.get(), ClientDTO.class);
        // Creating and returning an Optional of DTO.
        return Optional.of(dto);
    }


    /**
     * Method that adds a client to the database
     * @param request to be added
     * @return the added client
     */
    public ClientDTO addClient (ClientDTO request) {
        Client client = new ModelMapper().map(request, Client.class);
        // Re-setting password to a encoded one
        client.setPassword(passwordEncoder.encode(request.getPassword()));
//        client.setBirthdate(LocalDate.now());
        client.setRole(Role.USER);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    /**
     * Deletes a client from the databases
     * @param id from the client to be deleted
     */
    public void removeClient (Long id) {
        if (findClientById(id).isEmpty()) {
            throw new ResourceNotFoundException("Client ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    /**
     * Method that updates a client on the database
     * @param id of the client to be updated
     * @param request to be updated
     * @return client after being updated on the database
     */
    public ClientDTO updateClient (Long id, ClientDTO request) {
        if (findClientById(id).isEmpty()) {
            throw new ResourceNotFoundException("Client ID " + id + " not found");
        }
        /**
         * Instantiating a variable of type Client with name client
         * which receives a ModelMapper and calls map method to
         * copy ClientDTO information received on the request.
         */
//        request.setId(id);
        Client client = new ModelMapper().map(request, Client.class);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    public boolean existsByEmail (String email) {
        return repository.existsByEmail(email);
    }

    public Optional<ClientDTO> getClientByEmail (String email) {
        Optional<Client> user = repository.findByEmail(email);
        if (user.isEmpty()) {
        throw new ResourceNotFoundException("User email: " + email + " could not be found.");
    }
    ClientDTO dto = new ModelMapper().map(user.get(), ClientDTO.class);
        return Optional.of(dto);
    }
}
