package hello.kakaobank;

import com.kakaobank.KakaobankApplication;
import com.kakaobank.search.controller.SimpleCorsFilter;
import com.kakaobank.search.service.kakao.KakaoSearchPlaceService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static java.awt.SystemColor.info;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.ResponseEntity;

@WebMvcTest(SimpleCorsFilter.class)
@ContextConfiguration(classes={KakaobankApplication.class})	// 부티 클래스를 설정해야 됨.
class KakaobankApplicationTests {

	@Autowired
	private MockMvc mockMvc;    // 컨트롤러 테스트를 위해 MockMvc 객체 의존관계 주입

	@MockBean
	private KakaoSearchPlaceService kakaoSearchPlaceService;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("")
	void KakaobankApplicationTests() throws Exception {

		String result = testRestTemplate.getForObject("/hello", String.class);
		assertThat(result).isEqualTo("hello gracenam");

		ResponseEntity<String> response = null;
		// given
		given(kakaoSearchPlaceService.callApi(any(HttpHeaders.class),anyString())).willReturn(response);

		MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
		info.add("name", "칩");

		// when
		/*ResultActions result =this.mockMvc.perform(get("/test")
				.param("name","jojang"))
				.andExpect(status().isOk())
				.andExpect(content().string("jojang"))
				.andDo(print());
*/
		// then
		/*result.andExpect(status().isOk()).andDo(print());
		assertThat(result).isEqualTo("안녕");*/
	}


}
