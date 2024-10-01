using System;
using System.IO;
using System.Reflection;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
using Newtonsoft.Json.Converters;
using Newtonsoft.Json.Serialization;
using SystemInfoService2DotNet.Facades;
using SystemInfoService2DotNet.Services;

namespace SystemInfoService2DotNet;

/// <summary>
///     Startup
/// </summary>
public class Startup
{
    /// <summary>
    ///     This method gets called by the runtime. Use this method to add services to the container.
    /// </summary>
    /// <param name="services"></param>
    public void ConfigureServices(IServiceCollection services)
    {
        services
            .AddControllers(_ => { })
            .AddNewtonsoftJson(opts =>
            {
                opts.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();
                opts.SerializerSettings.Converters.Add(new StringEnumConverter
                {
                    NamingStrategy = new CamelCaseNamingStrategy()
                });
            });
        services.AddEndpointsApiExplorer();
        services
            .AddSwaggerGen(c =>
            {
                c.EnableAnnotations(true, true);

                c.SwaggerDoc("1.0", new OpenApiInfo
                {
                    Title = "System Info Service 2 - DotNet",
                    Description = "System Info Service 2 - DotNet (ASP.NET Core 8.0)",
                    Contact = new OpenApiContact
                    {
                        Name = "Dávid Laurovič",
                        Email = "david.laurovic@tuni.fi"
                    },
                    License = new OpenApiLicense
                    {
                        Name = "NoLicense",
                        Url = new Uri("https://www.apache.org/licenses/LICENSE-2.0.html")
                    },
                    Version = "1.0"
                });
                c.IncludeXmlComments(
                    $"{AppContext.BaseDirectory}{Path.DirectorySeparatorChar}{Assembly.GetEntryAssembly()?.GetName().Name}.xml");
            });
        services
            .AddSwaggerGenNewtonsoftSupport();

        services.AddAutoMapper(Assembly.GetExecutingAssembly());
        services.AddScoped<ISystemInfoFacade, SystemInfoFacade>();
        services.AddScoped<ISystemInfoService, SystemInfoService>();
    }

    /// <summary>
    ///     This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
    /// </summary>
    /// <param name="app"></param>
    /// <param name="env"></param>
    public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
    {
        if (env.IsDevelopment())
            app.UseDeveloperExceptionPage();
        else
            app.UseHsts();

        app.UseHttpsRedirection();
        app.UseDefaultFiles();
        app.UseStaticFiles();
        app.UseSwagger(c => { c.RouteTemplate = "openapi/{documentName}/openapi.json"; })
            .UseSwaggerUI(c =>
            {
                c.RoutePrefix = "openapi";
                c.SwaggerEndpoint("/openapi/1.0/openapi.json", "System Info Service 2 - DotNet");
            });
        app.UseRouting();
        app.UseEndpoints(endpoints => { endpoints.MapControllers(); });
    }
}