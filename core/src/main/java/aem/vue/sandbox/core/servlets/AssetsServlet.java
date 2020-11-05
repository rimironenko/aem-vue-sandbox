package aem.vue.sandbox.core.servlets;

import com.adobe.granite.rest.Constants;
import com.day.cq.dam.api.DamConstants;
import org.apache.jackrabbit.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;

@Component(service = {Servlet.class})
@SlingServletPaths({AssetsServlet.SERVLET_MAPPING})
public class AssetsServlet extends SlingSafeMethodsServlet {

    public static final String SERVLET_MAPPING = "/services/aem-vue-sandbox/assets";

    private static final Logger LOG = LoggerFactory.getLogger(AssetsServlet.class);

    private static final String PATH_PARAM = "path";
    private static final String EMPTY_ARRAY = "[]";
    private static final String ASSET_PATH_KEY = "assetPath";
    private static final String ASSET_NAME_KEY = "assetName";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        RequestParameter pathParameter = request.getRequestParameter(PATH_PARAM);
        String jsonResponse = EMPTY_ARRAY;
        if (pathParameter != null) {
            String path = pathParameter.getString();
            try {
                jsonResponse = getAssetsAsJson(path, request.getResourceResolver());
            } catch (JSONException e) {
                LOG.error("failed to get assets: ", e);
            }
        }
        response.setCharacterEncoding(Constants.DEFAULT_CHARSET);
        response.setContentType(Constants.CT_JSON);
        response.getWriter().write(jsonResponse);
    }

    private String getAssetsAsJson(String path, ResourceResolver resourceResolver) throws JSONException {
        JSONArray response = new JSONArray();
        Resource assetsResource = resourceResolver.getResource(path);
        if (assetsResource != null) {
            Iterator<Resource> iterator = assetsResource.listChildren();
            while (iterator.hasNext()) {
                Resource asset = iterator.next();
                if (isAsset(asset)) {
                    JSONObject assetObject = new JSONObject();
                    assetObject.put(ASSET_PATH_KEY, asset.getPath());
                    assetObject.put(ASSET_NAME_KEY, asset.getName());
                    response.put(assetObject);
                }
            }
        }
        return response.toString();
    }

    private boolean isAsset(Resource asset) {
        ValueMap assetValueMap = asset.getValueMap();
        if (assetValueMap.containsKey(JcrConstants.JCR_PRIMARYTYPE)) {
            return DamConstants.NT_DAM_ASSET.equals(assetValueMap.get(JcrConstants.JCR_PRIMARYTYPE));
        }
        return false;
    }
}

