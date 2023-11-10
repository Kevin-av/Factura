package com.example.Base_1.controller

import com.example.Base_1.model.Ventas
import com.example.Base_1.service.VentasService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RestController
@RequestMapping("/ventas")   //endpoint
class VentasController {
    @Autowired
    lateinit var ventasService: VentasService

    @GetMapping
    fun list ():List <Ventas>{
        return ventasService.list()
    }

    @PostMapping
    fun save (@RequestBody ventas: Ventas): ResponseEntity<Ventas> {
        return ResponseEntity(ventasService.save(ventas), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody ventas: Ventas): ResponseEntity<Ventas> {
        return ResponseEntity(ventasService.update(ventas), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName (@RequestBody ventas: Ventas): ResponseEntity<Ventas> {
        return ResponseEntity(ventasService.update(ventas), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(ventasService.listById (id), HttpStatus.OK)

    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return ventasService.delete(id)
    }

}