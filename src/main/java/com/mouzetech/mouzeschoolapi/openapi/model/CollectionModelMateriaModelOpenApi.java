package com.mouzetech.mouzeschoolapi.openapi.model;

import com.mouzetech.mouzeschoolapi.api.model.output.MateriaModel;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;


@ApiModel("MateriasModel")
@Getter
@Setter
public class CollectionModelMateriaModelOpenApi {

    private MateriaModelEmbedded _embedded;
    private Links _links;

    @ApiModel("CidadeResumoModelEmbedded")
    @Getter
    @Setter
    private final class MateriaModelEmbedded {
        private List<MateriaModel> materias;
    }
}