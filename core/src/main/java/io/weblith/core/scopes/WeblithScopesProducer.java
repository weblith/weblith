package io.weblith.core.scopes;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.quarkus.runtime.LocalesBuildTimeConfig;
import io.weblith.core.i18n.*;

import io.quarkus.arc.DefaultBean;
import io.weblith.core.config.WeblithConfig;
import io.weblith.core.request.RequestContext;

import java.util.Locale;

@ApplicationScoped
public class WeblithScopesProducer {

    @Inject
    WeblithConfig weblithConfiguration;

    @DefaultBean
    @Produces
    @RequestScoped
    public FlashScope flash(RequestContext context) {
        return new FlashScopeHandler(weblithConfiguration, context);
    }

    @DefaultBean
    @Produces
    @RequestScoped
    public SessionScope session(RequestContext context) {
        return new SessionScopeHandler(weblithConfiguration, context);
    }

    @DefaultBean
    @Produces
    @ApplicationScoped
    public Messages messages(LocaleHandler localeHandler) {
        return new ResourceBundleMessages(localeHandler, weblithConfiguration);
    }

}
