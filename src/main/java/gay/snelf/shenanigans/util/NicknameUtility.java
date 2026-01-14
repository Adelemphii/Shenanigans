package gay.snelf.shenanigans.util;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.nameplate.Nameplate;
import com.hypixel.hytale.server.core.modules.entity.EntityModule;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class NicknameUtility {

    public static void updateNameplate(Ref<EntityStore> ref, Store<EntityStore> store, String text) {
        Nameplate nameplate = store.getComponent(ref, EntityModule.get().getNameplateComponentType());
        if(nameplate == null) return;

        nameplate.setText(text);

        store.replaceComponent(ref, EntityModule.get().getNameplateComponentType(), nameplate);
    }

//    public static void updateNickname(Ref<EntityStore> ref, Store<EntityStore> store, @Nullable String text, @Nullable Integer color) {
//        Nickname nickname = store.getComponent(ref, Nickname.getType());
//        if(nickname == null) {
//            nickname = new Nickname();
//        }
//
//        if(text != null) nickname.setNickname(text);
//        if(color != null) nickname.setColor(color);
//
//        store.replaceComponent(ref, Nickname.getType(), nickname);
//    }

//    public static Nickname getNickname(Ref<EntityStore> ref) {
//        Store<EntityStore> store = ref.getStore();
//        Nickname nickname = store.getComponent(ref, Nickname.getType());
//        if(nickname == null) nickname = new Nickname();
//
//        store.addComponent(ref, Nickname.getType(), nickname);
//        return nickname;
//    }
}
