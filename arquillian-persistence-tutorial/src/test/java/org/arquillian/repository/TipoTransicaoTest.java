package org.arquillian.repository;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.arquillian.example.TipoTransicao;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TipoTransicaoTest {
	
	@Deployment
	public static Archive<?> createDeployment(){
		// or jar packaging...
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(TipoTransicaoRepository.class.getPackage())
            .addClass(TipoTransicao.class)
            .addAsManifestResource("test-persistence.xml", "persistence.xml")
            .addAsManifestResource("jbossas-ds.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        
        // choose your packaging here
        return jar;
		
	}
	
	@PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Inject
    TipoTransicaoRepository pessoaDao;
    
    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }
    
    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from TipoTransicao").executeUpdate();
        utx.commit();
    }

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Inserting records...");
        
        em.persist(new TipoTransicao(10L, "TEP010", "estadosPosteriores", "manual"));
        
        utx.commit();
        // reset the persistence context (cache)
        em.clear();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
    
    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }
    
    @Test
    public void deveRetornarTipoDaTransicao() {
    	
    	TipoTransicao tipoTransicao = pessoaDao.obterPorDescricao("estadosPosteriores");
    	
    	assertEquals("TEP010", tipoTransicao.getCodigoTipoTransicao());
    	
    	
    }

}
