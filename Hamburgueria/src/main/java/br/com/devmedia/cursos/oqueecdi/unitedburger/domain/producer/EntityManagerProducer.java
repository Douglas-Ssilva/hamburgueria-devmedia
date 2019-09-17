package br.com.devmedia.cursos.oqueecdi.unitedburger.domain.producer;

import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.qualifier.ParceriasQualifier;
import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.qualifier.UnitedBurgerQualifier;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

    @Produces
    @ApplicationScoped //A EntityManagerFactory é um objeto muito pesado, assim o levanto apenas uma vez durante a app
    @UnitedBurgerQualifier
    public EntityManagerFactory getUnitedBurgerEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("unitedburger");
    }

    @Produces
    @RequestScoped // é usado um por ação de persistencia
    @UnitedBurgerQualifier
    //Quando chegar no parametro sabe que quero que seja levantado o unitedBurger
    //Deixar os dois como application a CDI nao sabe qual usar
    public EntityManager getUnitedBurgerEntityManager(@UnitedBurgerQualifier EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    @Produces
    @ApplicationScoped
    @ParceriasQualifier
    public EntityManagerFactory getParceriasEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("parcerias");
    }

    @Produces
    @RequestScoped
    @ParceriasQualifier
    public EntityManager getParceriasEntityManager(@ParceriasQualifier EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
    //Pois mantém mtas info na memoria. Disposes removem da memoria objetos completos
    //Any pois trabalho com mais de um banco de dados, afim de nao ter 2 metodos iguais
    public void fecharEntityManager(@Disposes @Any EntityManager manager) {
    	//Sabendo qual manager está sendo fechado
        String url = (String) manager.getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url");
        System.out.println(url);

        if (manager.isOpen()) {
            manager.close();
        }
    }
}
