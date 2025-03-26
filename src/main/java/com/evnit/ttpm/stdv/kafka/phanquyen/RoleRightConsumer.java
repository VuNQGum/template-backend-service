//package com.evnit.ttpm.stdv.kafka.phanquyen;
//
//import com.evnit.ttpm.stdv.model.Role;
//import com.evnit.ttpm.stdv.model.UserAuthority;
//import com.evnit.ttpm.stdv.model.UserAuthorityId;
//import com.evnit.ttpm.stdv.repository.RoleRepository;
//import com.evnit.ttpm.stdv.repository.UserAuthorityReopsitory;
//import com.evnit.ttpm.stdv.repository.UserRepository;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.text.ParseException;
//import java.util.List;
//
//@Component
//public class RoleRightConsumer {
//    private final RoleRepository roleRepository;
//    private final UserAuthorityReopsitory userAuthorityReopsitory;
//    private final UserRepository userRepository;
//
//    @Autowired
//    public RoleRightConsumer(RoleRepository roleRepository, UserAuthorityReopsitory userAuthorityReopsitory, UserRepository userRepository) {
//        this.roleRepository = roleRepository;
//        this.userAuthorityReopsitory = userAuthorityReopsitory;
//        this.userRepository = userRepository;
//    }
//
//    /**
//     * Kiểm tra nếu right là Authority thì lưu vào bảng Auth_ROLE
//     * Danh sách user gắn với role của QTTT sẽ gán vào bảng AUTH_USER_ROLE
//     * Nếu là Menu thì bỏ qua
//     * @param messages
//     * @param consumer
//     * @throws ParseException
//     * @throws JSONException
//     */
//
//    @KafkaListener(
//            groupId = "hrms-roleRightTopic-employe-consumer-group",
//            topics = "${kafka.topic.roleright}",
//            containerFactory = "EvnIdKafkaListenerContainerFactory"
//    )
//    @Transactional
//    public void listen(List<String> messages, Consumer consumer) throws ParseException, JSONException {
//        System.out.println("ROLE_RIGHT: " + messages.toString());
//        for(int index=0; index< messages.size(); index++){
//            JSONObject json = new JSONObject(messages.get(index));
//            try {
//                JSONObject payload = json.getJSONObject("payload");
//
//                if(payload.getString("action").equals("CREATE")){
//                    JSONObject rightModel = payload.getJSONObject("right");
//                    Long id = rightModel.getLong("id");
//                    if (id != null) {
//                        boolean isExist = roleRepository.existsById(id);
//                        if (!isExist && rightModel.getString("types").equals("AUTHORITY")) {
//                            Role role = new Role();
//                            role.setId(id);
//                            role.setRole(rightModel.getString("name"));
//                            role.setAppActive(true);
//                            role.setAppName("HRMS");
//                            System.out.println("RIGHT COMSUMER " + role.getId() + " " + role.getRole());
//                            roleRepository.saveAndFlush(role);
//                        }
//                    }
//                    JSONObject roleModel = payload.getJSONObject("role");
//                    //Lấy danh sách user gắn với ROLE
//                    if(!roleModel.isNull("user") && rightModel.getString("types").equals("AUTHORITY")){
//                        List<Object> user = roleModel.getJSONArray("user").toList();
//                        String nhomQuyen = roleModel.getString("role");
//                        if(!user.isEmpty() || user.size() > 0){
//                            for(Object username : user){
//                                Boolean checkExistUser = userRepository.existsById(username.toString());
//                                if(checkExistUser){
//                                    UserAuthorityId userAuthId = new UserAuthorityId(username.toString(), rightModel.getLong("id"), nhomQuyen);
//                                    UserAuthority userAuth = new UserAuthority(userAuthId);
//                                    System.out.println("RIGHT COMSUMER AUTH " + userAuthId.getUserId() + " " + userAuthId.getRoleId());
//                                    userAuthorityReopsitory.save(userAuth);
//                                }
//                            }
//                        }
//                    }
//                }else{
//                    JSONObject rightModel = payload.getJSONObject("right");
//                    JSONObject roleModel = payload.getJSONObject("role");
//                    //Lấy danh sách user gắn với ROLE
//                    if(!roleModel.isNull("user")){
//                        List<Object> user = roleModel.getJSONArray("user").toList();
//                        String nhomQuyen = roleModel.getString("role");
//                        if(!user.isEmpty() || user.size() > 0){
//                            for(Object username : user){
//
//                                UserAuthorityId userAuthId = new UserAuthorityId(username.toString(), rightModel.getLong("id"), nhomQuyen);
//                                Boolean exitsUserAuthId = userAuthorityReopsitory.existsById(userAuthId);
//                                if(exitsUserAuthId){
//                                    userAuthorityReopsitory.deleteById(userAuthId);
//                                }
//                                //UserAuthority userAuth = new UserAuthority(userAuthId);
//                            }
//                        }
//                    }
//
//                }
//            consumer.commitAsync();
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//}
