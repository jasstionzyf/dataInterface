






public class DataTransfer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    /* PfwService pfwService=(PfwService) SpringUtils.getBeanFromBeanContainer(PfwService.class, "conf/app.xml");
 	 HiberOperations ho=new MongoDbOps();
 	 Map map=null;
 	List<Catalog> catalogs=pfwService.query(map, Catalog.class);
 	for(Catalog  catalog:catalogs){
 		SpCatalog spCatalog=new SpCatalog();
 		spCatalog.setId(MongoDb.getInstance().getLongAutoId(SpCatalog.class));
 		spCatalog.setName(catalog.getName());
 		spCatalog.setParentCategoryId(0);
 		ho.save(spCatalog);
 		
 	}
 	List<com.yufei.infoExtractor.pfw.entity.ExternalLink> externalLinks2=pfwService.query(map, com.yufei.infoExtractor.pfw.entity.ExternalLink.class);
    for(com.yufei.infoExtractor.pfw.entity.ExternalLink externalLink:externalLinks2){
    	ExternalLink externalLink0=new ExternalLink();
    	externalLink0.setId(MongoDb.getInstance().getLongAutoId(ExternalLink.class));
    	externalLink0.setLink(externalLink.getLink());
    	ho.save(externalLink0);
    	
    }
    List<com.yufei.infoExtractor.pfw.entity.CatalogMapping> catalogMappings=pfwService.query(map, com.yufei.infoExtractor.pfw.entity.CatalogMapping.class);
	for(com.yufei.infoExtractor.pfw.entity.CatalogMapping catalogMapping:catalogMappings){
		CatalogMapping catalogMapping2=new CatalogMapping();
		catalogMapping2.setId(MongoDb.getInstance().getLongAutoId(CatalogMapping.class));
		catalogMapping2.setCatalogId(catalogMapping.getCatalogId());
		Map map1=new HashMap();
		map1.put("name", catalogMapping.getKeyWords());
		Serializable id=ho.find(SpCatalog.class, "name", catalogMapping.getKeyWords()).get(0);
		SpCatalog spCatalog=ho.find(SpCatalog.class, id);
		catalogMapping2.setSpCatalogId(spCatalog.getId());
		ho.save(catalogMapping2);
	}*/
		/*CatalogMapping catalogMapping1=new CatalogMapping();
		catalogMapping1.setCatalogId("50025135");
		catalogMapping1.setSpCatalogId(1);
		CatalogMapping catalogMapping=new CatalogMapping();
		catalogMapping.setCatalogId("50025174");
		catalogMapping.setSpCatalogId(2);
		PfwService.pfwService.save(catalogMapping1);
		PfwService.pfwService.save(catalogMapping);*/
		/*ExternalLink externalLink=new ExternalLink(),externalLink2=new ExternalLink();
		externalLink.setLink("http://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.8.6djRF7&cat=50025135&s=0&n=60&sort=p&style=g&post_fee=-1&miaosha=1&zk_type=0&vmarket=0&search_condition=16&pic_detail=1&area_code=110000&active=1#J_Filter");
		externalLink2.setLink("http://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.8.hdSJHH&cat=50025174&s=0&n=60&sort=p&style=g&post_fee=-1&miaosha=1&zk_type=0&vmarket=0&search_condition=23&pic_detail=1&area_code=110000&from=sn_1_rightnav&active=1#J_Filter");
	    PfwService.pfwService.save(externalLink);
	    PfwService.pfwService.save(externalLink2);*/



	    
	}

}
