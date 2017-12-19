package com.vanius.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vanius.cursomc.domain.Categoria;
import com.vanius.cursomc.domain.Cidade;
import com.vanius.cursomc.domain.Cliente;
import com.vanius.cursomc.domain.Endereco;
import com.vanius.cursomc.domain.Estado;
import com.vanius.cursomc.domain.Produto;
import com.vanius.cursomc.domain.enums.TipoCliente;
import com.vanius.cursomc.repositories.CategoriaRepository;
import com.vanius.cursomc.repositories.CidadeRepository;
import com.vanius.cursomc.repositories.ClienteRepository;
import com.vanius.cursomc.repositories.EnderecoRepository;
import com.vanius.cursomc.repositories.EstadoRepository;
import com.vanius.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;
	
	@Autowired
	private ProdutoRepository prod;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		repo.save(Arrays.asList(cat1,cat2));	
		prod.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));	
		
		estadoRepo.save(Arrays.asList(est1,est2));
		cidadeRepo.save(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 = new Cliente(null,"Maria", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("32120603", "997990125"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 03", "jardim", "38220834",cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Matos", "105", "sala 800", "centro", "38770834",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.save(Arrays.asList(cli1));
		
		enderecoRepo.save(Arrays.asList(e1, e2));
		
		
		
	}
}
