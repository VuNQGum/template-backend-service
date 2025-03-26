//package com.evnit.ttpm.stdv.kafka.phanquyen;
//
//import com.evnit.ttpm.stdv.model.UserAuthority;
//import com.evnit.ttpm.stdv.model.UserAuthorityId;
//import com.evnit.ttpm.stdv.repository.RoleRepository;
//import com.evnit.ttpm.stdv.repository.UserAuthorityReopsitory;
//import com.evnit.ttpm.stdv.repository.UserRepository;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class UserRoleConsumer {
//
//    private final UserAuthorityReopsitory userAuthorityRepository;
//    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserRoleConsumer(UserAuthorityReopsitory userAuthorityRepository, RoleRepository roleRepository, UserRepository userRepository) {
//        this.userAuthorityRepository = userAuthorityRepository;
//        this.roleRepository = roleRepository;
//        this.userRepository = userRepository;
//    }
//
//    @KafkaListener(
//            groupId = "hrms-user-roleTopic-employe-consumer-group",
//            topics = "${kafka.topic.roleuser}",
//            containerFactory = "EvnIdKafkaListenerContainerFactory"
//    )
//    @Transactional
//    public void listen(List<String> messages, Consumer consumer) {
//        System.out.println("ROLE_USER: " + messages.toString());
//        for(int index=0; index< messages.size(); index++) {
//            JSONObject json = new JSONObject(messages.get(index));
//            try {
//                JSONObject payload = json.getJSONObject("payload");
//                JSONObject roleModel = payload.getJSONObject("role");
//
//                if (!roleModel.isNull("rights")) {
//                    JSONArray rights = roleModel.optJSONArray("rights");
//                    JSONObject role = roleModel.getJSONObject("role");
//                    String nhomQuyen = role.getString("role");
//                    JSONObject user = payload.getJSONObject("user");
//                    String username = user.getString("username");
//
//                    if(payload.getString("action").equals("CREATE")){
//                        for (int i = 0; i < rights.length(); i++) {
//                            JSONObject right = rights.getJSONObject(i);
//                            Long id = right.getLong("id");
//                            String type = right.getString("types");
//                            if (id != null) {
//                                boolean isExist = roleRepository.existsById(id);
//                                if (isExist && type.equals("AUTHORITY")) {
//                                    Boolean checkExistUser = userRepository.existsById(username);
//                                    if (checkExistUser) {
//                                        UserAuthorityId userAuthorityId = new UserAuthorityId(username, right.getLong("id"), nhomQuyen);
//                                        UserAuthority userAuthority = new UserAuthority(userAuthorityId);
//                                        userAuthorityRepository.save(userAuthority);
//                                    }
//                                }
//                            }
//                        }
//                    }else{
//                        for (int i = 0; i < rights.length(); i++) {
//                            JSONObject right = rights.getJSONObject(i);
//                            Boolean checkExistUser = userRepository.existsById(username);
//                            Long id = right.getLong("id");
//                            String type = right.getString("types");
//                            if (id != null) {
//                                boolean isExist = roleRepository.existsById(id);
//                                if (isExist && type.equals("AUTHORITY")) {
//                                    if(checkExistUser){
//                                        UserAuthorityId userAuthorityId = new UserAuthorityId(username,right.getLong("id"), nhomQuyen);
//                                        userAuthorityRepository.deleteById(userAuthorityId);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                consumer.commitAsync();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
