package org.activiti.designer.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestFailure;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
//import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class WorkFlowTest {
		
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ProcessEngineFactoryBean processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private FormService formService ;
    @Test
    public void testEvent() throws InterruptedException {
    //����activiti��������Ӧ�����ý�ɫ����,ִ��һ��
    	/* testSaveUser("10000", "����");
 	  	testSaveUser("10001", "����");
    	testSaveGroup("T_MAN");
    	testSaveMembership("10001", "T_MAN");*/
    	 /* RepositoryService :  ����Ϳ��� �����������̶���(������һ������ÿ�����ڵĽṹ����Ϊ) �Ĳ���
  		����֮�⣬�������
    	��ѯ�����еķ����������̶��塣
    	��ͣ�򼤻��������Ӧȫ�����ض����̶��塣 ��ͣ��ζ�����ǲ�����ִ���κβ����ˣ������Ƕ�Ӧ�ķ��������
    	��ö�����Դ�����ǰ����ڷ���������ļ��� �������Զ����ɵ�����ͼ��
    	������̶����pojo�汾�� ��������ͨ��java�������̣�������ͨ��xml��*/
    	/*  activiti���û���ɫ������õ��ĸ����ֱ��Ӧ��صĲ���
		act_id_group �û����
		act_id_user �û���
		act_id_membership �û�����Ĺ���������ʵ�ֶ�Զࣻ
		act_id_info �û���Ϣ��*/
    		/*���β���ʹ�����ñ�form*/ 
    		repositoryService.createDeployment()
            .addClasspathResource("com/lin/workflow/leaveWork.bpmn") 
            // .addClasspathResource("diagrams/form/approve.form")  //������ñ�����src/main/resources/
            .deploy();//����act_re_procdef���¼��ÿ�β������̶���
    		
    		/*ɾ������������� 
    		 * ��ͨɾ���������ǰ������������ִ�е����̣������쳣
    	   	  repositoryService.deleteDeployment(deploymentId);
    	   	   ����ɾ��,��ɾ���͵�ǰ������ص�������Ϣ��������ʷ
    	   	  repositoryService.deleteDeployment(deploymentId, true);*/
    	System.out.println("=============================���̲�����ɣ���=============================================");        
    		//������ѯ���� 
      		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
      		query.processDefinitionKey("vacationRequest");//ͨ��key��ȡ��Ӳ�ѯ����
      		 	// .processDefinitionName("My process")//ͨ��name��ȡ
      			// .orderByProcessDefinitionId()//����ID����
      			   //.processDefinitionKeyLike(processDefinitionKeyLike)//֧��ģ����ѯ
      				//.listPage(firstResult, maxResults)//��ҳ
      		List<ProcessDefinition> pds = query.list();//��ȡ��������ϸ
      		//.singleResult()//��ȡ��������ϸ
      		for (ProcessDefinition pd : pds) {
      			System.out.println("=============================��ȡkeyΪleaveWork����������Ϣ=============================================");
      			System.out.println("ID:"+pd.getId()+",NAME:"+pd.getName()+",KEY:"+pd.getKey()+",VERSION:"+pd.getVersion()+",RESOURCE_NAME:"+pd.getResourceName()+",DGRM_RESOURCE_NAME:"+pd.getDiagramResourceName());
      		}
        System.out.println("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());//����������
        
        //ҵ�����ݺ�activiti����ͬ����ʹ��identityService������Ӧ��act_id_*����������û���ɫ��
        //  IdentityService:  �������������£�ɾ������ѯ...��Ⱥ����û�
        //identityService.setAuthenticatedUserId("10000");��������֤���û�
        Map<String, Object> variables = new HashMap<String, Object>();//��Ϊ������ñ�������
        variables.put("employeeName", "10000");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "��ؼ���Ϣ��ô��!");
        //��������
       /* RuntimeService : ��������һ�����̶������ʵ��,��ȡ�ͱ��� ���̱���,��ѯ����ʵ����ִ��*/
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);  //������
        System.out.println("=======================��ǰ����ʵ�������ǣ�"+ runtimeService.createProcessInstanceQuery().count());
        //��startProcessInstanceByKey�ķ�ʽ����������id
        System.out.println("===========�������̣�����Ϊ�����̵�ID��name��ProcessDefinitionId��ProcessDefinitionKey============");
        System.out.println(processInstance.getId()+"����"+processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId()+"   DefinitionId:DefinitionKey   "+processInstance.getProcessDefinitionKey());
        System.out.println("ҵ������==============================��"+processInstance.getBusinessKey());
        //Ҫ��ҵ����leave_list����������������ֶ�PROCESS_INSTANCE_ID varchar(64);
       /* start �¼����ǲ����͵ģ��Ӹ����Ͻ������¼����κ�ʱ�򣩻�һֱ�ȴ�ֱ����������
        * start �¼��У�����ָ������� activiti �����е�����
        * formKey��ָ��һ���û�����������������ʵ��ʱ��д�ı�ģ��activiti:formKey="org/activiti/examples/taskforms/request.form"
        * initiator����ʶ��������ʱ���洢��֤�û� id �ı�����<startEvent id="request" activiti:initiator="initiator" /> 
        * ������ tye-finally ���У�ʹ�÷��� IdentityService.setAuthenticatedUserId(String)��������֤���û�
        * try {  identityService.setAuthenticatedUserId("bono"); 
		 runtimeService.startProcessInstanceByKey("someProcessKey"); } 
		 finally {  identityService.setAuthenticatedUserId(null); } 
        * */
        /*TaskService : �����������йصĹ���
		��ѯ������û����������
		���� ��������������Щ����������ʵ���޹ء�
		�ֹ����������ִ���ߣ�������Щ�û�ͨ�����ַ�ʽ�����������
		���첢���һ������������ζ��һ����������Ϊ�����ִ���ߣ�
 		������û������������������ζ�š����������Ҫ������顱�� ͨ����˵���кܶ��ִ�����ʽ��*/
        //ֱ�ӷ�����û����������ͨ�� TaskService ����ȡList<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit").list(); 
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("T_MAN").list();//��ȡ�û��顰T_MAN�����е�����
       /* if(tasks.size()==0){
        	  taskService.claim("handleRequest", "T_MAN");//���죬��������Ա�������б� ����ʧ
        }*/
        for (Task task : tasks) {
         System.out.println("=============������+ִ����=========="+task.getName() + " : " + task.getAssignee());
   //ͨ���������񣬽���ר����Ϊ������Ĵ����ˣ�ͬʱ�������ӻ����������Ա�������б� ����ʧtaskService.claim(task.getId(), "fozzie"); 
          taskService.claim(task.getId(), "T_MAN");//���죬��������Ա�������б� ����ʧ
        }
        findTask("T_MAN");
        //ʹ�� taskService ͨ��������µ��߼����ܻ�ø�����List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("kermit").list(); 
        Task task = tasks.get(0);//��ȡ��һ������
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "����ʱ����ȣ��������!");//������
        taskService.complete(task.getId(), taskVariables);//��ɴ�������
       /* for (Task task : tasks) {
            taskService.complete(task.getId());
            System.out.println("=========��ɴ�������========��"+task.getName() + " : " + task.getId() + " ��ɣ������� ");
        }*/
        System.out.println("==================================������ɵȴ�ʮ��========================================");
        Thread.sleep(10 * 1000);//�ȴ�10��
        //������һ������ڵ�	
        /*System.out.println("====================10001��������=======================================");
        tasks = taskService.createTaskQuery().taskAssignee("10001").list(); //��ѯ������
        for (Task task : tasks) {
            System.out.println("==========�û�10001��������================"+task.getName() + " : " + task.getAssignee());
            taskService.claim(task.getId(), "10001");//����
        }*/
        //���� FormService.getRenderStartForm ��ȡ����Ⱦ���ı��ַ�����
        //String processDefinitionId= (String) FormService.getRenderedStartForm("dd") ;
       /* tasks = taskService.createTaskQuery().taskAssignee("10001").list();
        for (Task task : tasks) {
            taskService.complete(task.getId());
            System.out.println("================�û�10001�������============"+task.getName() + " : " + task.getId() + " ��ɣ������� ");
        }
        System.out.println("=======================10001�������===========================================");*/
        /*  Activiti��������TaskService taskService;
        taskService.setAssignee(String taskId, String userId);
        taskService.claim(String taskId, String userId);
        taskService.setOwner(String taskId, String userId);
      ����������������������setAssignee��claim����������������������ʱ��
        claim����������Ƿ��Ѿ������죬�������������׳�ActivitiTaskAlreadyClaimedException ��setAssignee������������ļ�飬
        ����������������Ч��һ�¡�
        setOwner��setAssignee����������setOwnerʵ�ڴ�������ʱʹ�ã�����������Ĺ����ߣ�����ʱ��setAssignee�����ʱ��������ߣ�
        �ٸ�������˵����˾�ܾ��������и�����taskA��ȥ��ʵһ�±���ȵĲ��񱨱��������ֺ�æûʱ�䣬
        ���ǽ�������ί�и���������а�����ʱ����Ӧ����ô����
        taskService.setOwner(taskA.getId(), �ܾ���.getId());taskService.setAssignee/claim(taskA.getId(), ����.getId());*/
        tasks = taskService.createTaskQuery().taskCandidateGroup("T_MAN").list();//��ȡT_MAN���µ����� 
        for (Task task1 : tasks) {
        	System.out.println("=================�����µĿ�������: =================" + task1.getName());   
        	//taskService.claim(task.getId(), "kermit"); 
        }
/*//�����ݹ�״̬�µ����̲��ܿ���
        repositoryService.suspendProcessDefinitionByKey("vacationRequest");
        try {
          runtimeService.startProcessInstanceByKey("vacationRequest");
        } catch (ActivitiException e) {
          e.printStackTrace();
        }*/
        taskVariables = new HashMap<String, Object>();
        variables.put("numberOfDays", new Integer(2));
        variables.put("vacationMotivation", "��ؼ���Ϣ��ô��!");
        variables.put("resendRequest", "false");//�Ƿ������鿴
        //����Ա�����
        tasks = taskService.createTaskQuery().taskAssignee("10000").list();//�������񣿣�������${resendRequest == 'true'}���������죩ԭ���������ûͨ������ͽ�����ɲ��������³���
     /*   ɾ������Ĺ�ϵ������ɾ����ʷ�����Ȼ������Ѱ���������ɾ����˳������
      * act_hi_procinst ����ʵ����ʷ��  
      * act_hi_actinst �����ʷ������ɵ����� 
      * act_hi_taskinst ����������ʷ�� ��ֻ��Ӧ�ڵ���UserTask�ģ� 
      * act_hi_actinst  ���нڵ���ʷ�� ����Ӧ���̵����нڵ�Ļ��ʷ���ӿ�ʼ�ڵ�һֱ�������ڵ��м�����нڵ�Ļ���ᱻ��¼�� 
      * act_hi_variable ���̱�����ʷ�� 
      * act_ru_identitylink
      * act_ru_task ��������� ��ֻ��Ӧ�ڵ���UserTask�ģ�
      * act_ru_variable ����ִ�е����̱����� 
      * act_ru_execution ����ִ�е�ִ�ж��������ִ�ж���ID������ʵ��ID������ж���߳̿�������ʵ��ID��һ��
      * */
     //  taskService.complete(tasks.get(0).getId(),taskVariables);
  for (Task task1 : tasks) {
	  //�Ƚ����������
	  taskService.claim(task1.getId(), "10000");//���죬��������Ա�������б� ����ʧ
	  /*�������̱����ļ��ַ�ʽ
	   * 1������setVariables���������������������һ�����̱���
	   */	 
	  taskService.setVariables(task1.getId(), variables );
	  		/**
	  		 * 2�����������ʱ�������̱���
	  		 * taskService.complete(task1.getId(),taskVariables);
	  		 */
	  		taskService.complete(task1.getId());
	  		System.out.println("================�û�Ա���ٴ��������============"+task1.getName() + " : " + task1.getId() + " ��ɣ������� ");
        /**3��ͨ��������
         * public void setVarByObj() throws Exception {
        String processInstanceId = "1901";
        Task task =taskService.createTaskQuery().taskAssignee("manager").processInstanceId(processInstanceId ).singleResult();
        Form form = new Form();//���javabeanʵ����Serializable�ӿ�
 		form.setName("������");
        form.setContent("������������Ҫ���3��");
        taskService.setVariable(task.getId(), "form", form);
    	} */
  }
  
 /* String candidateUser = "10000";  
  List<Task> list = processEngine.getProcessEngineConfiguration()//  
                  .createTaskQuery()//  
                  .taskCandidateUser(candidateUser)//�����ߣ��������ѯ  
                  .list();  
  if(list!=null && list.size()>0){ 
      for(Task task1:list){
          System.out.println("����ID��"+task1.getId());  
          System.out.println("����İ����ˣ�"+task1.getAssignee());  
          System.out.println("�������ƣ�"+task1.getName());  
          System.out.println("����Ĵ���ʱ�䣺"+task1.getCreateTime());  
          System.out.println("����ʵ��ID��"+task1.getProcessInstanceId());  
          System.out.println("#######################################");  
      } 
  }  */
  
//  HistoryService:  �ṩ��Activiti�����������ʷ����
        HistoricProcessInstance hpInstance = 
                historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstance.getId()).singleResult();
        System.out.println("========================���̽�����ʱ��=============: " + hpInstance.getEndTime());
  
        //��ѯ10000��ʷ����====��ʵ�����Ƿ����
    String assignee = "10000";
    List<HistoricTaskInstance> htis = historyService.createHistoricTaskInstanceQuery()//������ʷ�����ѯ����
			//����һ����ʷ��ϸ��Ϣ��ѯ����
			//.createHistoricDetailQuery()
			//������ʷ����ʵ����ѯ����
			//.createHistoricProcessInstanceQuery()
			.taskAssignee(assignee)//���ݽ����˲�ѯ��ʷ����
			.list();//�����������
	for (HistoricTaskInstance hti : htis) {
		System.out.println("=====================10000��ID:"+hti.getId()+",����ʵ��ID:"+hti.getProcessInstanceId()+",���������:"+hti.getAssignee()+"����ִ�ж���ID:"+hti.getExecutionId());
	}
	System.out.println("=========================================���̽���============================================");
  //  ����һЩ����
/*   FormService:  һ����ѡ����,��������ṩ�� ������ �� ����� ��������
  ManagementService : ��ʹ��Activiti�Ķ��ƻ����л����ϲ����õ��� �����Բ�ѯ���ݿ�ı�ͱ��Ԫ���ݡ�
  ���⣬���ṩ�˲�ѯ�͹����첽�����Ĺ��ܡ�*/
}
  /*  ����û�����*/
/*    public void testSaveUser(String userid,String username){
       // IdentityService identityService=processEngine.getIdentityService();
        User user1=new UserEntity(); // ʵ�����û�ʵ��
        user1.setId(userid);
        user1.setLastName(username);
        identityService.saveUser(user1); // ����û�
           }*/
        
        public void testFinduser(String userid){
        	
    }
 
    /**
     * ����ɾ���û�
     */
    public void testDeleteUser(String userid){
       // IdentityService identityService=processEngine.getIdentityService();
        identityService.deleteUser(userid); 
    }
    /*
     * ���������
     */
    public void testSaveGroup(String groupid){
       // IdentityService identityService=processEngine.getIdentityService();
        Group group1=new GroupEntity(); // ʵ������ʵ��
        group1.setId(groupid);
        identityService.saveGroup(group1);
    }
    /**
     * ����ɾ����
     */
    public void testDeleteGroup(String groupid){
       // IdentityService identityService=processEngine.getIdentityService();
        identityService.deleteGroup(groupid);
    }
    /**
     * ��������û����������ϵ
     */
    public void testSaveMembership(String userid,String groupid){
       // IdentityService identityService=processEngine.getIdentityService();
    	identityService.createMembership(userid, groupid);
    }
    /**
     * ����ɾ���û����������ϵ
     */
    public void testDeleteMembership(String userid,String gropid){
      //  IdentityService identityService=processEngine.getIdentityService();
        identityService.deleteMembership(userid, gropid);
    }
    public void findTask(String groupid){
        List<Task> taskList=taskService // �������Service
            .createTaskQuery() // ���������ѯ
            //.taskAssignee("����") // ָ��ĳ����
            .taskCandidateGroup(groupid)//ָ����
          //  .taskCandidateUser("zhangsan") // ָ����ѡ��
            .list();
        for(Task task:taskList){
            System.out.println("����ID:"+task.getId()); 
            System.out.println("��������:"+task.getName());
            System.out.println("���񴴽�ʱ��:"+task.getCreateTime());
            System.out.println("����ί����:"+task.getAssignee());
            System.out.println("����ʵ��ID:"+task.getProcessInstanceId());
        }
    }
    /* 
	*�������̶��� ����zip��
     */
/*    @Test
    public void deploymentProcessDefinition_zip(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/HelloWorld.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine.getRepositoryService()//�����̶���Ͳ��������ص�Service
                        .createDeployment()//����һ���������
                        .name("���̶���")//��Ӳ�������
                        .addZipInputStream(zipInputStream)//���zip�ļ��Ĳ���
                        .deploy();//��ɲ���
        System.out.println("����ID��"+deployment.getId());
        System.out.println("��������:"+deployment.getName());
    }*/
    /**
     * ɾ�����̶���
     */
    public void deleteProcessDefinition(){
      //  IdentityService identityService=processEngine.getIdentityService();
    	repositoryService.deleteDeployment("15001",true);
    	System.out.println("ɾ���ɹ�");
    }
}