//package com.evnit.ttpm.stdv.kafka.phanquyen;
//
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
//public class RoleConsumer {
//    private final UserAuthorityReopsitory userAuthorityReopsitory;
//
//    @Autowired
//    public RoleConsumer(UserAuthorityReopsitory userAuthorityReopsitory) {
//        this.userAuthorityReopsitory = userAuthorityReopsitory;
//    }
//
//    /**
//     * Role của QTTT là nhóm quyền của SmartEVN
//     * Delete Role -> xóa ở bảng Auth-User-Authority theo nhóm quyền
//     */
//    @KafkaListener(
//            groupId = "hrms-roleTopic-employe-consumer-group",
//            topics = "${kafka.topic.role}",
//            containerFactory = "EvnIdKafkaListenerContainerFactory"
//    )
//    @Transactional
//    public void listen(List<String> messages, Consumer consumer) throws ParseException, JSONException {
//        System.out.println("ROLE: " + messages.toString());
//        for(int index=0; index< messages.size(); index++){
//            JSONObject json = new JSONObject(messages.get(index));
//            try {
//                JSONObject payload = json.getJSONObject("payload");
//                if(payload.getString("action").equals("DELETE")){
//                    JSONObject roleModel = payload.getJSONObject("role");
//                    userAuthorityReopsitory.deleteByNhomQuyen(roleModel.getString("role"));
//                }
//                consumer.commitAsync();;
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
