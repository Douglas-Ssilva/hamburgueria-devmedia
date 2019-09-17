package br.com.devmedia.cursos.oqueecdi.unitedburger.service;

import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.dao.ContatoDao;
import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.model.Contato;

import javax.inject.Inject;

public class ContatoService {

    @Inject
    private ContatoDao contatoDao;

    public void cadastrar(Contato contato) {
        contatoDao.cadastrar(contato);
    }
}
