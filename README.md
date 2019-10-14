# KnowledgeCenterWithJPA
 

# Set Up Steps:

 1) Create schema in MYSQL using the query , Tables will be created by JPA.
	   CREATE SCHEMA `knowledgecenter` ;


 2) Change DB username and Password in AppConfig.java (line No. 25,26)

 3) Build the project using the command  -->  mvn clean install

 4) Run the application in maven embedded tomcat  using the command --> mvn tomcat7:run

 5) Swagger endpoints can be found at the url

	   http://localhost:8080/swagger-ui.html

 6) I am not able to test Spring Security login API from Swagger/Postman, so I disabled security in this app . Security Related           Configuration can be found at SecurityConfig.java.

# Description About REST APIs
1. The Author creates the knowledgeBase, added a table  knowledgebase which contains fields knowledgeId, knowledgeDescription, supported Language.

   * Added a Model file "KnowledgeBase" with the above fields and setters/getters.
   * In KnowledgeBaseController , added a REST API for creating a knowledgeBase with "/knowledge" and HttpMethod as POST. The parameter will be a request body with the knowledgeBase to be created. If creation is successful, the author gets the successful message with HttpStatus code as CREATED.

2. Each knowledgeBase can have many categories ,added a table Category which contains fields id,CategoryName,KnowledgeBase_id.

    * Added a Model file "Category" with the above fields and setter/getters.
    * In KnowledgeBaseController, added a REST API for creating a category with mapping "/knowledge/category" and HttpMethod as POST. The parameter will be a request body with category to be created.If creation is successful , the author gets the successful message with HttpStatus code as CREATED.

3. Each category can have any number of documents so added a table Document which contains fields id, DocumentType,category_id,content,title,question,answer,locale,createdts.
 
    * Added a Model file "Document" with the above fields and setters/getters.
    * In DocumentController , added a REST API for creating documents with mapping "/document" with HttpMethod as POST. The parameter will be list of documents to be created. If creation is successful , the author gets the successful message with HttpStatus code as CREATED.


4. REST API for retrieving the documents which matches the keyword.
  
    * In DocumentController. added a REST API for retrieving the documents with mapping "/document?search={keyword}" with HttpMethod as GET. The parameter will be list of documents which contains the given keyword and added a condition to only get the documents which are created 12hrs ago as document creation may take time. If there are no documents found with the given keyword, an DocumentNotFoundException is thrown and handled.


Added extra REST APIS for getting the documentsByCategory and creating a document of type faq and article seperately.

1.  REST API for creating a document of type FAQ.

     * In DocumentController , added a REST API for creating a document of FAQ with mapping "/document/faq" with HttpMethod as POST.The parameter will be a FAQdocument  to be created with fields question, Answer, Category. If creation is successful,the author getsthe successful message with HttpStatus code as CREATED.

2.  REST API for creating a document of type Article.

    * In DocumentController, added a REST API for creating a document of Article with mapping "/document/article" with HttpMethod as POST.The parameter will be a ArticleDocument  to be created with fields title, content, Category. If creation is successful , the author gets the successful message with HttpStatus code as CREATED.

3.  REST API for retrieving  document which belongs to a specific category.
  
    * In DocumentController, added a REST API for retrieving the documents of category with mapping "document?categoryId={id}" with HttpMethod as GET.

# SpringSecurity

   For Role Based API, I have implemented Spring Security InMemoryAuthentication. I have created two users in Security Config and assigned roles like Author and Reviewer. When I am trying to hit the API to get/create documents then it will be redirected to login page, once we enters the username and password , Spring Security makes use of the cookie to determine whether it is a relevant role or not. 
   
 Referred the link for Spring Security : https://www.baeldung.com/spring-security-multiple-auth-providers

