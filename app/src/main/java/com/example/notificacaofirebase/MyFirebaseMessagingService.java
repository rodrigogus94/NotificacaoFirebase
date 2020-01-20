package com.example.notificacaofirebase;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage notificacao) {

        if(notificacao.getNotification() != null){

            String titulo = notificacao.getNotification().getTitle();
            String corpo = notificacao.getNotification().getBody();

            Log.i("Notificacao","Titulo: " + titulo + "Corpo: " + corpo);

            enviarNotificacao(titulo, corpo);


        }

    }

    private void enviarNotificacao(String titulo, String corpo) {

        //Configurações para notificação
        String canal = getString(R.string.app_default_notification_channel_id);
        Uri uriSom = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

         //Criando Notificação
        NotificationCompat.Builder notificacao = new NotificationCompat.Builder(
                this,canal
        ).setContentTitle(titulo)
                .setContentText(corpo)
                .setSmallIcon(R.drawable.ic_restaurant_black_24dp)
                .setSound(uriSom);

        //Recupera um notificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Verificação de versão do Android a parti do Orion para configurar canal de notificação
        

        //envio notificação
        notificationManager.notify(0, notificacao.build());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }
}
