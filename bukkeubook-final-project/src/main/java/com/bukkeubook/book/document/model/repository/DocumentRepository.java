package com.bukkeubook.book.document.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.document.model.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

}
