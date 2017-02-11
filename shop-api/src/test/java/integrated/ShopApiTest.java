package integrated;

public class ShopApiTest {
	
//	public void testLastModifiedWithEventHandler() {
//		
//		// 값을 설정하고 이벤트 핸들러가 구동되어 최신값으로 적용되었는지 확인하자.
//		// 설정 된 값 보다 커야 하고, 검사하는 시간보다는 작아야 한다.
//		
//		Shop shop = shopRepository.findOne(1L);
//		Date beforeDate = new Date();
//		shop.setLastModified(beforeDate);
//		
//		sleep();
//		shopRepository.save(shop);
//		sleep();
//		
//		Shop modifiedShop = shopRepository.findOne(1L);
//		Date modifiedDate = modifiedShop.getLastModified();
//		
//		Date afterDate = new Date();
//		
//		System.err.println(beforeDate.getTime());
//		System.err.println(modifiedDate.getTime());
//		System.err.println(afterDate.getTime());
//		
//		assertTrue(ShopEventHandler.class + " not registerd.", beforeDate.compareTo(modifiedDate) > 0);
//		assertTrue(ShopEventHandler.class + " not registerd.", afterDate.compareTo(modifiedDate) < 0);
//		
//	}
//	
//	private void sleep() {
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			fail("thread sleep failed.");
//		}
//	}
	
}
