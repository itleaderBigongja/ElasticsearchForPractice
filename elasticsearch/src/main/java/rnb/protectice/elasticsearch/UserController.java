package rnb.protectice.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private UserDocumentRepository userDocumentRepository;
    public UserController(UserDocumentRepository userDocumentRepository) {
        this.userDocumentRepository = userDocumentRepository;
    }

    @PostMapping()
    public UserDocument CreateUser(@RequestBody UserCreateRequestDTO requestDTO) {

        // requestDTO로 들어오는 값으로 user 객체를 생성
        UserDocument user = new UserDocument(
            requestDTO.getId(),
            requestDTO.getName(),
            requestDTO.getAge(),
            requestDTO.getIsActive()
        );

        // 생성된 객체로 userDocumentRepository.save()에 담아 users 인덱스에 저장
        return userDocumentRepository.save(user);
    }

    // users 인덱스에서 존재하는 모든 유저의 정보를 찾는 메서드
    // Page<UserDocument> 메서드의 반환 타입으로, 조회된 UserDocument 목록과 함께
    // 총 페이지 수, 현재 페이지 번호, 전체 항목 수와 같은 페이지네이션 정보를 담고 있는 객체입니다.
    @GetMapping()
    public Page<UserDocument> findUsers(@RequestParam int page, @RequestParam int size) {
        // findAll(PageRequest.of(현재 페이지 번호, 페이지 내에서 보여줄 개수))
        return userDocumentRepository.findAll(PageRequest.of(page, size));
    }

    // 특정 ID의 값을 가진 유저의 정보를 찾는 메서드
    // @PathVariable 애노테이션 : URL로 넘겨받은 {id}의 값을 사용
    @GetMapping("/{id}")
    public UserDocument findUserById(@PathVariable String id) {
        // ElasticsearchDB 에서 파라미터로 넘겨받은 id로 USER를 찾는데 존재하면
        // 해당 id의 값을 가진 User의 정보를 리턴해주고 존재하지 않으면, Optional을 통해
        // RuntimeException을 발생시키고 ErrorMessage는 존재하지 않은 사용자 입니다로 출력
        return userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 사용자 입니다."));
    }

    @PutMapping("/{id}")
    public UserDocument updateUser(
            @PathVariable String id,                            // http:localhost:9080/users/1
            @RequestBody UserUpdateRequestDTO requestDTO) {     // { "name":value", "age":value, "isActive":value }
        // 변경대상 유저를 id로 찾는다.
        UserDocument existingUser = userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 아이디입니다."));
        existingUser.setName(requestDTO.getName());         // 파라미터로 받은 이름으로 변경
        existingUser.setAge(requestDTO.getAge());           // 파라미터로 받은 나이로 변경
        existingUser.setIsActive(requestDTO.getIsActive()); // 파라미터로 받은 활성여부로 변경

        System.out.println("변경될 대상의 정보 : " + existingUser.toString());
        return userDocumentRepository.save(existingUser);   // 새로 셋팅된 값으로 유저 정보 수정( save는 저장 or 수정 가능 )
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        UserDocument deleteUser = userDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 아이디입니다."));
        userDocumentRepository.delete(deleteUser);
    }
}
