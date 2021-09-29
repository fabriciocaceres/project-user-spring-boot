package com.project_user.service;

import com.project_user.entity.ApplicationUser;
import com.project_user.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;

    public ResponseEntity<ApplicationUser> save(ApplicationUser user) throws Exception {
        try{
            return ResponseEntity.ok(this.applicationUserRepository.save(user));
        } catch (Exception e) {
            throw new Exception("Erro ao salvar usuário");
        }
    }

    public ResponseEntity<ApplicationUser> update(ApplicationUser user) throws Exception {
        try{
            var dbUser = this.applicationUserRepository.findById(user.getId()).get();

            dbUser.setBirthDate(user.getBirthDate());
            dbUser.setName(user.getName());

            return ResponseEntity.ok(this.applicationUserRepository.saveAndFlush(dbUser));
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar usuário");
        }
    }

    public ResponseEntity delete(Long id) throws Exception {
        try{
            var dbUser = this.applicationUserRepository.findById(id).get();

            this.applicationUserRepository.delete(dbUser);

            return ResponseEntity.ok("Deletado com sucesso");
        } catch (Exception e) {
            throw new Exception("Erro ao deletar usuário");
        }
    }

    public ResponseEntity<List<ApplicationUser>> findAll() throws Exception {
        try{
            return ResponseEntity.ok(this.applicationUserRepository.findAll());
        } catch (Exception e) {
            throw new Exception("Erro ao listar usuários");
        }
    }

    public ResponseEntity<ApplicationUser> findById(Long id) throws Exception {
        try{
            return ResponseEntity.ok(this.applicationUserRepository.findById(id).get());
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuário");
        }
    }

    public ResponseEntity addFile(String file, long id) {
        var dbUser = this.applicationUserRepository.findById(id).get();
        dbUser.setFile(file);

        return ResponseEntity.ok(this.applicationUserRepository.saveAndFlush(dbUser));
    }
}
