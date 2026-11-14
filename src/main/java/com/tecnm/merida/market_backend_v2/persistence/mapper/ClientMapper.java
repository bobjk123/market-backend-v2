package com.tecnm.merida.market_backend_v2.persistence.mapper;

import com.tecnm.merida.market_backend_v2.domain.Client;
import com.tecnm.merida.market_backend_v2.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "id", target = "clientId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "lastName"),
            @Mapping(source = "celular", target = "cellphone"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "correoElectronico", target = "email")
    })
    Client toClient(Cliente cliente);
    List<Client> toClients(List<Cliente> clientes);

    @InheritInverseConfiguration
    @Mapping(target = "compras", ignore = true)
    Cliente toCliente(Client client);
}
