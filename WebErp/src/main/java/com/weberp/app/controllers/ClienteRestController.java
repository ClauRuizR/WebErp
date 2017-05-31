package com.weberp.app.controllers;

import java.util.List;

import com.weberp.app.common.view.BaseRestController;
import com.weberp.app.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.domain.Cliente;
import com.weberp.app.services.ClienteService;

@RestController
@RequestMapping("/rest/clientes")
public class ClienteRestController extends BaseRestController<Cliente,Long>{

	@Autowired
	public ClienteRestController(ClienteRepository repo) {
		super(repo);
	}
}
