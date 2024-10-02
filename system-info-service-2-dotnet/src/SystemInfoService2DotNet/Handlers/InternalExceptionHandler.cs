using System;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Diagnostics;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using SystemInfoService2DotNet.Models;

namespace SystemInfoService2DotNet.Handlers;

public class InternalExceptionHandler(ILogger<InternalExceptionHandler> logger) : IExceptionHandler

{
    private const string InternalExceptionMsg = "An internal exception has occurred while executing the request.";


    public async ValueTask<bool> TryHandleAsync(HttpContext context, Exception exception,
        CancellationToken cancellationToken)
    {
        logger.LogError(exception, InternalExceptionMsg);

        const string contentType = "application/problem+json";
        context.Response.ContentType = contentType;
        context.Response.StatusCode = (int)HttpStatusCode.InternalServerError;
        await context.Response.WriteAsJsonAsync(new ErrorDTO(InternalExceptionMsg), cancellationToken);

        return true;
    }
}