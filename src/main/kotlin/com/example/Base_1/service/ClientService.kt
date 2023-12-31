package com.example.Base_1.service

import com.example.Base_1.model.Client
import com.example.Base_1.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

import javax.swing.table.DefaultTableModel

@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list(pageable: Pageable, model:Client): Page<Client> {
        val matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return clientRepository.findAll(Example.of(model, matcher), pageable)
    }
    fun save(client: Client): Client {
        try{
            return clientRepository.save(client)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(client: Client): Client {
        try {
            clientRepository.findById(client.id)
                    ?: throw Exception("ID no existe")

            return clientRepository.save(client)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(client: Client): Client {
        try{
            val response = clientRepository.findById(client.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                fullname=client.fullname //un atributo del modelo
            }
            return clientRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Client?{clientRepository
        return clientRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = clientRepository.findById(id)
                    ?: throw Exception("ID no existe")
            clientRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}