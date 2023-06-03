package com.melihgencturk.rentacar.repository;

import com.melihgencturk.rentacar.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
