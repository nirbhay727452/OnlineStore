package com.onlinestore.onlinestore.Security;

import com.onlinestore.onlinestore.Models.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

    @Entity
    @Getter
    @Setter
    public class Role extends BaseModel {
        private String role;
    }

