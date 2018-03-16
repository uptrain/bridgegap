package com.mycompany.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="MY_CUSTOM_CLASS")
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
@AdminPresentationClass(friendlyName = "MyCustomClassImpl")
public class MyCustomClassImpl implements MyCustomClass {

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(generator = "MyCustomClassId")
        @GenericGenerator(
                        name="MyCustomClassId",
                        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
                        parameters = {
                                        @Parameter(name="segment_value", value="MyCustomClassImpl"),
                                        @Parameter(name="entity_name", value="com.mycompany.sample.domain.MyCustomClassImpl")
                        }
        )
        @Column(name = "MY_CUSTOM_CLASS_ID")
        protected Long id;

        @Column(name = "NAME", nullable = false)
        @AdminPresentation(friendlyName = "MyCustomClassImpl_name", order = 1,
                        prominent = true, gridOrder = 1)
        protected String name;

        @Override
        public Long getId() {
                return id;
        }

        @Override
        public void setId(Long id) {
                this.id = id;
        }

        @Override
        public String getName() {
                return name;
        }

        @Override
        public void setName(String name) {
                this.name = name;
        }
}