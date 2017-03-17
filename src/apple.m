#import <Foundation/Foundation.h>

void LOG(const char *s)
{
	NSLog(@"%s", s);
}

// Admob stuff
#ifdef ADMOB
#import <GoogleMobileAds/GoogleMobileAds.h>
#import <GoogleMobileAds/GADInterstitialDelegate.h>

#include <allegro5/allegro.h>
#include <allegro5/allegro_iphone.h>
#include <allegro5/allegro_iphone_objc.h>

static GADInterstitial *interstitial;

void requestNewInterstitial()
{
	GADRequest *request = [GADRequest request];
	// Request test ads on devices you specify. Your test device ID is printed to the console when
	// an ad request is made.
	//request.testDevices = @[ kGADSimulatorID, @"FIXME-FOR-TESTING" ];
	[interstitial loadRequest:request];
}

@interface Ad_Delegate : NSObject<GADInterstitialDelegate>
{
}
- (void)interstitialDidDismissScreen;
@end

@implementation Ad_Delegate
- (void)interstitialDidDismissScreen
{
	requestNewInterstitial();
}
@end

static Ad_Delegate *ad_delegate;

void initAdmob()
{
    ad_delegate = [[Ad_Delegate alloc] init];
	interstitial = [[GADInterstitial alloc] initWithAdUnitID:@"ca-app-pub-5564002345241286/1715397850"];
    interstitial.delegate = ad_delegate;
	requestNewInterstitial();
}

void showAd()
{
	ALLEGRO_DISPLAY *display = al_get_current_display();

	[[NSOperationQueue mainQueue] addOperationWithBlock:^{
		if (interstitial.isReady) {
			[interstitial presentFromRootViewController:al_iphone_get_window(display).rootViewController];
		}
	}];
}
#endif // ADMOB

// localization

const char *get_apple_language()
{
	static char buf[100];

    NSString *str = [[NSLocale preferredLanguages] objectAtIndex:0];
    
	if ([str hasPrefix:@"de"]) {
		strcpy(buf, "german");
	}
	else if ([str hasPrefix:@"fr"]) {
		strcpy(buf, "french");
	}
	else {
		strcpy(buf, "english");
	}

	return buf;
}
