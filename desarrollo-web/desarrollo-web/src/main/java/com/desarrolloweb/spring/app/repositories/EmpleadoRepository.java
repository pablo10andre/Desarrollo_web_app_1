package com.desarrolloweb.spring.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.desarrolloweb.spring.app.entities.Empleado;

@Repository
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long>{


}
