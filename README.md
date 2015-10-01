# OA
a OA system 

 1 It have thhree ways peobblem case the lazy load fail
   1) close the lazy load in the *.hbm.xml such as <many-to-one name="approver" class="User" column="approverId" lazy="false"></many-to-one>
   2) using the filter open-session in videw which seting in the web.xml
   <filter> 
   <filter-name>OpenSessionInViewFilter</filter-name> 
     <filter-class>org.springframework.orm.hibernate4.support.OpenSessionInViewFilter</filter-class> 
         <init-param>  
     <param-name>sessionFactoryBeanName</param-name>  
         <param-value>sessionFactory</param-value>  
       </init-param>  
         </filter> 
	3) setting  int hibernate perporty under sessionFactory
	<property name="hibernateProperties"> 
		 <props>
                 .......
	           <prop key="hibernate.enable_lazy_load_no_trans">true</prop>
            </props>
		</property>
		
   
 