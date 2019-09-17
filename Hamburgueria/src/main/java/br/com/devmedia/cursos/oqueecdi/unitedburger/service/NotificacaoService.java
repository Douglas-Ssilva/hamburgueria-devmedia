package br.com.devmedia.cursos.oqueecdi.unitedburger.service;

import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.dao.NotificacaoDao;
import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.model.Notificacao;

import javax.inject.Inject;

public class NotificacaoService {

    @Inject
    private NotificacaoDao notificacaoDao;

    public void cadastrar(Notificacao notificacao) {
        notificacaoDao.cadastrar(notificacao);
    }
}
