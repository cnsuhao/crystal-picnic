#ifndef APPLE_H
#define APPLE_H

#ifdef __cplusplus
extern "C" {
#endif

#ifdef ADMOB
void initAdmob();
void showAd();
#endif

const char *get_apple_language();

#ifdef __cplusplus
}
#endif

#endif // APPLE_H
