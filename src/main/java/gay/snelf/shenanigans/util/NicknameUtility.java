package gay.snelf.shenanigans.util;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.nameplate.Nameplate;
import com.hypixel.hytale.server.core.modules.entity.EntityModule;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.component.Nickname;

public class NicknameUtility {

    public static void updateNameplate(Ref<EntityStore> ref, Store<EntityStore> store, String text) {
        Nameplate nameplate = store.getComponent(ref, EntityModule.get().getNameplateComponentType());
        if(nameplate == null) return;

        nameplate.setText(text);

        store.replaceComponent(ref, EntityModule.get().getNameplateComponentType(), nameplate);
    }

    public static Nickname updateNicknameComponent(Ref<EntityStore> ref, Store<EntityStore> store, String text, int color) {
        Nickname nicknameComponent = store.getComponent(ref, Nickname.getComponentType());
        if(nicknameComponent == null) {
            nicknameComponent = new Nickname(text, color);
            store.addComponent(ref, Nickname.getComponentType(), nicknameComponent);
            return nicknameComponent;
        }
        if(text != null) nicknameComponent.setText(text);
        if(color != -1) nicknameComponent.setColor(color);

        store.replaceComponent(ref, Nickname.getComponentType(), nicknameComponent);
        updateNameplate(ref, store, nicknameComponent.getText());
        return nicknameComponent;
    }
}
