package com.codestates.mainproject.oneyearfourcut.domain.gallery.entity;

import com.codestates.mainproject.oneyearfourcut.global.auditable.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Gallery extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galleryId;
    private String title;
    private String content;
}
