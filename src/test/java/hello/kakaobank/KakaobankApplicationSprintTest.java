package hello.kakaobank;

import com.kakaobank.KakaobankApplication;
import com.kakaobank.search.controller.SimpleCorsFilter;
import com.kakaobank.search.service.kakao.KakaoSearchPlaceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes={KakaobankApplication.class})
@AutoConfigureMockMvc
class KakaobankApplicationSprintTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	KakaoSearchPlaceService kakaoSearch;

	@Autowired
	private TestRestTemplate template1;

	@Test
	@DisplayName("")
	void KakaobankApplicationSprintTest() throws Exception {
	}


}
