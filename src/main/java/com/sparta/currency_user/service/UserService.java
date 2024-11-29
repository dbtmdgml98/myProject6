package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.UserRequestDto;
import com.sparta.currency_user.dto.UserResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("entity");
//    EntityManager em =  emf.createEntityManager();
//    EntityTransaction transaction = em.getTransaction();
    @PersistenceContext
    private EntityManager em;

    public UserResponseDto findById(Long id) {
        return new UserResponseDto(findUserById(id));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User savedUser = userRepository.save(userRequestDto.toEntity());
        return new UserResponseDto(savedUser);
    }

    // D: 고객이 삭제될 때 해당 고객이 수행한 모든 환전 요청도 삭제
    // 부모=고객, 자식=환전
    @Transactional
    public void deleteUserById(Long id) {

//        this.findUserById(id);
//        userRepository.deleteById(id);

        // cascade 제거
        User findUser = em.find(User.class, id);
        em.remove(findUser);    // 컬렉션이 제거되었으므로 환전요청도 모두 제거된다.
    }

}
