//package com.evnit.ttpm.stdv.kafka.phanquyen;
//
//import com.evnit.ttpm.stdv.model.Role;
//import com.evnit.ttpm.stdv.repository.RoleRepository;
//import com.evnit.ttpm.stdv.repository.UserAuthorityReopsitory;
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
//public class RightConsumer {
//
//    private final RoleRepository roleRepository;
//    private final UserAuthorityReopsitory userAuthorityReopsitory;
//
//
//    @Autowired
//    public RightConsumer(RoleRepository roleRepository, UserAuthorityReopsitory userAuthorityReopsitory) {
//        this.roleRepository = roleRepository;
//        this.userAuthorityReopsitory = userAuthorityReopsitory;
//    }
//
//    /**
//     * Kiểm tra nếu xóa quyền right ở quản trị tập trung -> xóa theo roleId ở SmartEVN
//     * @param messages
//     * @param consumer
//     * @throws ParseException
//     * @throws JSONException
//     */
//    @KafkaListener(
//            groupId = "hrms-rightTopic-employe-consumer-group",
//            topics = "${kafka.topic.right}",
//            containerFactory = "EvnIdKafkaListenerContainerFactory"
//    )
//    @Transactional
//    public void listen(List<String> messages, Consumer consumer) throws ParseException, JSONException {
//        System.out.println("RIGHT: " + messages.toString());
//        for(int index=0; index< messages.size(); index++){
//            JSONObject json = new JSONObject(messages.get(index));
//            try {
//                JSONObject payload = json.getJSONObject("payload");
//                JSONObject rightModel = payload.optJSONObject("right");
//                String type = rightModel.getString("types");
//                if(payload.getString("action").equals("DELETE") && type.equals("AUTHORITY")){
//                    Long id = rightModel.getLong("id");
//                    roleRepository.deleteById(id);
//                    userAuthorityReopsitory.deleteByRoleId(id);
//                }else{
//                    // Chi luu Authority, khong luu Menu
//                    Long id = rightModel.getLong("id");
//                    if (id != null) {
//                        boolean isExist = roleRepository.existsById(id);
//                        if (!isExist && type.equals("AUTHORITY")) {
//                            Role role = new Role();
//                            role.setId(id);
//                            role.setRole(rightModel.getString("name"));
//                            role.setAppActive(true);
//                            role.setAppName(rightModel.optString("description"));
//                            System.out.println("RIGHT COMSUMER " + role.getId() + " " + role.getRole());
//                            roleRepository.save(role);
//                        }
//                    }
//                }
//                consumer.commitAsync();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//
//    }
//
//
//
//}