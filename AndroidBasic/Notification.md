# Notification通知

代码来自：https://github.com/moduth/blockcanary/blob/master/library/src/main/java/com/github/moduth/blockcanary/BlockCanary.java

    @TargetApi(HONEYCOMB)
    private void notify(String contentTitle, String contentText, PendingIntent pendingIntent) {
        NotificationManager notificationManager = (NotificationManager)
                BlockCanaryContext.get().getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification;
        if (SDK_INT < HONEYCOMB) {
            notification = new Notification();
            notification.icon = R.drawable.block_canary_notification;
            notification.when = System.currentTimeMillis();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notification.defaults=Notification.DEFAULT_SOUND;// add sound by chiahaolu
            // TODO SUPPORT
            //notification.setLatestEventInfo(BlockCanaryContext.get().getContext(), contentTitle, contentText, pendingIntent);
            notification.setLatestEventInfo(BlockCanaryContext.get().getContext(), contentTitle, contentText, pendingIntent);
        } else {
            Notification.Builder builder = new Notification.Builder(BlockCanaryContext.get().getContext())
                    .setSmallIcon(R.drawable.block_canary_notification)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND);// add sound by chiahaolu
            if (SDK_INT < JELLY_BEAN) {
                notification = builder.getNotification();
            } else {
                notification = builder.build();
            }
        }
        notificationManager.notify(0xDEAFBEEF, notification);
    }