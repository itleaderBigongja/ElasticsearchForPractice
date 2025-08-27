package rnb.protectice.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

// ElasticsearchRepository<T, ID> 인터페이스는 제네릭 타입으로 두 가지를 받는다.
// T : Elasticsearch에 저장되는 문서의 타입
// ID : 해당 문서의 ID 필드 타입.
// 따라서 UserDocument라는 엔티티를 관리하고, 이 엔티티의 ID 타입은 String이라는 것을 스프링 데이터에게 알려주는 역할을 한다.
public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, String> {

}
