package integrated;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tm.shop.ShopApiApplication;
import com.tm.shop.TestFramework;
import com.tm.shop.config.RepositoryInitializer;
import com.tm.shop.entity.Shop;
import com.tm.shop.entity.Value;

@ContextConfiguration(classes={ShopApiApplication.class, ShopApiTest.class})
@WebAppConfiguration
public class ShopApiTest extends TestFramework {
	
//	@Configuration
//	@EnableJpaRepositories(considerNestedRepositories = true)
//	static class Config extends RepositoryRestConfigurerAdapter {
//
//		public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//			config.setDefaultPageSize(1);
//		}
//	}

	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;
	
	@Autowired
	private RepositoryInitializer initializer;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	public void testGetShopList() throws Exception {
		MvcResult andReturn = mvc.perform(get("/shop"))
								.andExpect(status().isOk())
								.andExpect(jsonPath("$.page.totalElements").value(RepositoryInitializer.DEFAULT_COUNT))
								.andExpect(jsonPath("$._embedded.shop[0].name").value("ilovenail-0"))
								.andExpect(jsonPath("$._embedded.shop[0].name").exists())
								.andExpect(jsonPath("$._embedded.shop[0].description").exists())
								.andExpect(jsonPath("$._embedded.shop[0].rating").exists())
								.andExpect(jsonPath("$._embedded.shop[0].shopType").exists())
								.andExpect(jsonPath("$._embedded.shop[0].discounted").exists())
								.andExpect(jsonPath("$._embedded.shop[0].valueScore").exists())
								.andExpect(jsonPath("$._embedded.shop[0].telNumber").doesNotExist())
								.andExpect(jsonPath("$._embedded.shop[0].viewCnt").doesNotExist())
								.andExpect(jsonPath("$._embedded.shop[0].likeCnt").doesNotExist())
								.andExpect(jsonPath("$._embedded.shop[0].lastModified").doesNotExist())
								.andReturn();
		
	}
	
	@Test
	public void testPageable() throws Exception {
		int pageNumber = 1;
		mvc.perform(get("/shop?page="+pageNumber))
								.andExpect(status().isOk())
								.andExpect(jsonPath("$.page.totalElements").value(RepositoryInitializer.DEFAULT_COUNT))
								.andExpect(jsonPath("$.page.number").value(pageNumber))
								.andExpect(jsonPath("$._embedded.shop[0].name").exists());
	}
	
	@Test
	public void testSortable() throws Exception {
		
		mvc.perform(get("/shop?sort=name,desc"))
								.andExpect(status().isOk())
								.andExpect(jsonPath("$.page.totalElements").value(RepositoryInitializer.DEFAULT_COUNT))
								.andExpect(jsonPath("$._embedded.shop[0].name").value("ilovenail-9"));		
	}
	
	@Test
	public void testGetShop() throws Exception {
		
		Shop firstShop = shopRepository.findAll().iterator().next();
		long shopId = firstShop.getId();
		
		mvc.perform(get("/shop/" + shopId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.description").exists())
				.andExpect(jsonPath("$.rating").exists())
				.andExpect(jsonPath("$.shopType").exists())
				.andExpect(jsonPath("$.discounted").exists())
				.andExpect(jsonPath("$.valueScore").exists())
				.andExpect(jsonPath("$.telNumber").exists())
				.andExpect(jsonPath("$.viewCnt").exists())
				.andExpect(jsonPath("$.likeCnt").exists())
				.andExpect(jsonPath("$.lastModified").exists());
		
	}
	
	@Test
	public void testGetPhotoList() throws Exception {
		Shop firstShop = shopRepository.findAll().iterator().next();
		long shopId = firstShop.getId();
		
		mvc.perform(get("/shop/" + shopId +"/photoList"))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$._embedded.photo[0].url").exists())
							.andExpect(jsonPath("$._embedded.photo[0].sourceType").exists());
							
	}
	
	@Test
	public void testCalculateValue() throws Exception {
		mvc.perform(get("/values-calc")).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateValueConfiguration() throws Exception {
		
		Value firstValue = valueRepository.findAll().iterator().next();
		long valueId = firstValue.getId();
		
		String requestJson = "{\"description\":\"changed\",\"applyRate\":0.5}";

		mvc.perform(put("/values/" + valueId).contentType(MediaType.APPLICATION_JSON)
        							.content(requestJson.getBytes()))
									.andExpect(status().is2xxSuccessful());

		
		mvc.perform(get("/values/" + valueId))
									.andExpect(status().isOk())
									.andExpect(jsonPath("$.description").value("changed"))
									.andExpect(jsonPath("$.applyRate").value(0.5));

		
	}
	
}
