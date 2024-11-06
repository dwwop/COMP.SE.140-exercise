using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;
using SystemInfoService2DotNet.Facades;
using SystemInfoService2DotNet.Models;

namespace SystemInfoService2DotNet.Controllers;

/// <summary>
/// </summary>
[ApiController]
public class SystemInfoApiController(ISystemInfoFacade facade) : ControllerBase
{
    /// <summary>
    ///     Get information about system including IP address, running processes, disk space and time since last boot.
    /// </summary>
    /// <response code="200">OK</response>
    /// <response code="500">Unexpected server error</response>
    [HttpGet]
    [Route("/")]
    [SwaggerOperation("GetInformation")]
    [SwaggerResponse(200, type: typeof(List<SystemInfoDTO>), description: "OK")]
    [SwaggerResponse(500, type: typeof(ErrorDTO), description: "Unexpected server error")]
    public virtual IActionResult GetInformation()
    {
        return Ok(new List<SystemInfoDTO> { facade.GetSystemInfo() });
    }

    /// <summary>
    ///     Shutdowns service 2s after receiving request
    /// </summary>
    /// <response code="200">OK</response>
    /// <response code="500">Unexpected server error</response>
    [HttpPost]
    [Route("/shutdown")]
    [SwaggerOperation("Shutdown")]
    [SwaggerResponse(204)]
    [SwaggerResponse(500, type: typeof(ErrorDTO), description: "Unexpected server error")]
    public virtual IActionResult Shutdown()
    {
        facade.Shutdown();
        return NoContent();
    }
}